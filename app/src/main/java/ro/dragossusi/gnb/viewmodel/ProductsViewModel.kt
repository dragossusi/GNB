package ro.dragossusi.gnb.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import ro.dragossusi.api.gnb.ProductsApi
import ro.dragossusi.gnb.base.BaseViewModel
import ro.dragossusi.gnb.model.ProductEntity
import ro.dragossusi.gnb.model.TransactionEntity
import java.math.BigDecimal
import java.util.*
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
class ProductsViewModel(private val api: ProductsApi) : BaseViewModel() {

    private val _products = MutableLiveData<List<ProductEntity>>()

    val products: LiveData<List<ProductEntity>>
        get() = _products

    fun getProducts() {
        viewModelScope.launch {
            showProgress()
            val response = api.getProducts()
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
            val map = mutableMapOf<String, MutableList<TransactionEntity>>()
            items.forEach {
                val transactions = map.getOrPut(it.name) {
                    mutableListOf()
                }
                transactions += TransactionEntity(
                    amount = BigDecimal(it.amount),
                    currency = Currency.getInstance(it.currency)
                )
            }
            val products = map.map { (key, value) ->
                ProductEntity(
                    key,
                    value
                )
            }
            hideProgress()
            _products.value = products
        }
    }

    class Factory @Inject constructor(private val api: ProductsApi) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ProductsApi::class.java)
                .newInstance(api)
        }
    }

    @dagger.Module
    class Module {

        @Provides
        fun viewModel(
            activity: DaggerAppCompatActivity,
            factory: Factory
        ): ProductsViewModel = ViewModelProvider(activity, factory)
            .get(ProductsViewModel::class.java)

    }

}