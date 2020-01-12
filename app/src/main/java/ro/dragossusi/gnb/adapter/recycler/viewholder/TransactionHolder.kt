package ro.dragossusi.gnb.adapter.recycler.viewholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_transaction.view.*
import ro.dragossusi.gnb.R
import ro.dragossusi.gnb.model.TransactionEntity

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
class TransactionHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : RecyclerView.ViewHolder(layoutInflater.inflate(R.layout.item_transaction, parent, false)) {

    private val textAmount: TextView = itemView.text_amount

    fun bind(transaction: TransactionEntity) {
        @SuppressLint("SetTextI18n")
        textAmount.text = "${transaction.amount} ${transaction.currency.currencyCode}"
    }

}