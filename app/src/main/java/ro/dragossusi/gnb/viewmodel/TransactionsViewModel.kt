package ro.dragossusi.gnb.viewmodel

import android.os.Bundle
import androidx.lifecycle.*
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultDirectedWeightedGraph
import ro.dragossusi.api.gnb.RatesApi
import ro.dragossusi.gnb.base.BaseViewModel
import ro.dragossusi.gnb.graph.CurrencyEdge
import ro.dragossusi.gnb.model.ProductEntity
import ro.dragossusi.gnb.model.TransactionEntity
import ro.dragossusi.gnb.ui.transactions.TransactionsActivity
import java.math.BigDecimal
import javax.inject.Inject

/**
 * GNB
 *
 * Copyright (C) 2020  Rachieru Dragos-Mihai
 *
 * GNB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * GNB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GNB.  If not, see [License](http://www.gnu.org/licenses/) .
 *
 */
class TransactionsViewModel(private val ratesApi: RatesApi) : BaseViewModel() {

    private val _total = MutableLiveData<BigDecimal>()

    val total: LiveData<BigDecimal>
        get() = _total

    private val _product = MutableLiveData<ProductEntity>()

    val product: LiveData<ProductEntity>
        get() = _product


    fun initData(extras: Bundle) {
        _product.value = extras.getParcelable(TransactionsActivity.EXTRA_TRANSACTION)
    }

    fun getRates() {
        viewModelScope.launch {
            showProgress()
            val response = ratesApi.getRates()
            if (!response.isSuccessful) {
                hideProgress()
                showError(Throwable("Failed request"))
                return@launch
            }
            val items = response.body() ?: run {
                hideProgress()
                showError(Throwable("No body"))
                return@launch
            }
            val graph =
                DefaultDirectedWeightedGraph<String, CurrencyEdge>(CurrencyEdge::class.java)
            items.forEach {
                graph.addVertex(it.from)
                graph.addVertex(it.to)
                graph.addEdge(it.from, it.to, CurrencyEdge(it.rate.toBigDecimal()))
            }
            hideProgress()
            _total.value = calculateTotal(graph, product.value!!.transactions)
        }
    }

    private fun calculateTotal(
        graph: Graph<String, CurrencyEdge>,
        transactions: List<TransactionEntity>
    ): BigDecimal {
        var total = BigDecimal(0)
        transactions.forEach {
            var amount = it.amount
            val path =
                DijkstraShortestPath.findPathBetween(graph, it.currency.currencyCode, CURRENCY)
            path.edgeList.forEach { edge ->
                amount = (edge.weight * (amount)).setScale(2, BigDecimal.ROUND_HALF_EVEN)
            }
            total += amount
        }
        return total
    }

    class Factory @Inject constructor(private val ratesApi: RatesApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(RatesApi::class.java)
                .newInstance(ratesApi)
        }
    }

    @dagger.Module
    class Module {

        @Provides
        fun viewModel(
            activity: DaggerAppCompatActivity,
            factory: Factory
        ): TransactionsViewModel = ViewModelProvider(activity, factory)
            .get(TransactionsViewModel::class.java)

    }

}

const val CURRENCY = "EUR"