package ro.dragossusi.gnb.ui.transactions

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_transactions.*
import ro.dragossusi.gnb.R
import ro.dragossusi.gnb.adapter.recycler.TransactionsAdapter
import ro.dragossusi.gnb.base.BaseActivity
import ro.dragossusi.gnb.viewmodel.CURRENCY
import ro.dragossusi.gnb.viewmodel.TransactionsViewModel
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
class TransactionsActivity : BaseActivity<TransactionsViewModel>() {

    @Inject
    lateinit var adapter: TransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)
        setSupportActionBar(toolbar)
        recycler_transactions.layoutManager = LinearLayoutManager(this)
        recycler_transactions.adapter = adapter
        viewModel.initData(intent?.extras!!)
        viewModel.progress.observe(this, Observer {
            progress_circular.visibility = it
        })
        viewModel.product.observe(this, Observer {
            if (it != null) {
                supportActionBar!!.title = it.name
                adapter.setItems(it.transactions)
                viewModel.getRates()
            }
        })
        viewModel.total.observe(this, Observer {
            @SuppressLint("SetTextI18n")
            text_total.text = "$it $CURRENCY"
        })
    }

    companion object {
        const val EXTRA_TRANSACTION = "extra-transaction"
    }

}