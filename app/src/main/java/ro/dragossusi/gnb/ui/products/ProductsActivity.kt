package ro.dragossusi.gnb.ui.products

import android.content.Intent
import ro.dragossusi.gnb.R
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_products.*
import ro.dragossusi.gnb.adapter.recycler.ProductsAdapter
import ro.dragossusi.gnb.base.BaseActivity
import ro.dragossusi.gnb.model.ProductEntity
import ro.dragossusi.gnb.ui.transactions.TransactionsActivity
import ro.dragossusi.gnb.viewmodel.ProductsViewModel
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
class ProductsActivity : BaseActivity<ProductsViewModel>() {

    @Inject
    lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setSupportActionBar(toolbar)
        recycler_products.layoutManager = LinearLayoutManager(this)
        recycler_products.adapter = adapter
        viewModel.progress.observe(this, Observer {
            progress_circular.visibility = it
        })
        viewModel.products.observe(this, Observer {
            if (it != null)
                adapter.setItems(it)
        })
        viewModel.getProducts()
    }

    fun onProductClicked(product: ProductEntity) {
        startActivity(Intent(this, TransactionsActivity::class.java).apply {
            putExtra(TransactionsActivity.EXTRA_TRANSACTION, product)
        })
    }

}