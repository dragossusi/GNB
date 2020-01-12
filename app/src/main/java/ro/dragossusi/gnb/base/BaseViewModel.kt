package ro.dragossusi.gnb.base

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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
abstract class BaseViewModel : ViewModel() {

    private val _progress = MutableLiveData<Int>(View.GONE)

    val progress: LiveData<Int>
        get() = _progress

    private val _error = MutableLiveData<ErrorState>(ErrorState(false, null))

    val error: LiveData<ErrorState>
        get() = _error

    open fun showProgress() {
        _progress.value = View.VISIBLE
    }

    open fun hideProgress() {
        _progress.value = View.GONE
    }

    fun showError(t: Throwable) {
        _error.value = ErrorState(true, t)
    }

}