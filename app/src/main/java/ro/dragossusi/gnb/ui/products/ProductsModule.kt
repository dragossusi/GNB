package ro.dragossusi.gnb.ui.products

import dagger.Module
import dagger.Provides
import dagger.android.support.DaggerAppCompatActivity
import ro.dragossusi.gnb.adapter.recycler.ProductsAdapter
import ro.dragossusi.gnb.ui.transactions.TransactionsActivity
import ro.dragossusi.gnb.viewmodel.ProductsViewModel

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
@Module(includes = [ProductsViewModel.Module::class])
class ProductsModule {

    @Provides
    fun adapter(activity: ProductsActivity) = ProductsAdapter(activity, activity::onProductClicked)


    @Provides
    fun activity(activity: ProductsActivity): DaggerAppCompatActivity = activity

}