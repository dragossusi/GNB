package ro.dragossusi.gnb.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.dragossusi.gnb.adapter.recycler.viewholder.ProductHolder
import ro.dragossusi.gnb.base.adapter.recycler.ListRecyclerAdapter
import ro.dragossusi.gnb.model.ProductEntity

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
class ProductsAdapter(
    context: Context,
    val onProductClicked: (ProductEntity) -> Unit
) : ListRecyclerAdapter<ProductEntity, ProductHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(layoutInflater, parent).apply {
            itemView.setOnClickListener {
                onProductClicked(items[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(items[position].name)
    }
}