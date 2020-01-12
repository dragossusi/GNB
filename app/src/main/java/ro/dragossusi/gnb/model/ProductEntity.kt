package ro.dragossusi.gnb.model

import android.os.Parcel
import android.os.Parcelable

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
class ProductEntity(
    val name:String,
    val transactions: List<TransactionEntity>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createTypedArrayList(TransactionEntity)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(transactions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductEntity> {
        override fun createFromParcel(parcel: Parcel): ProductEntity {
            return ProductEntity(parcel)
        }

        override fun newArray(size: Int): Array<ProductEntity?> {
            return arrayOfNulls(size)
        }
    }

}