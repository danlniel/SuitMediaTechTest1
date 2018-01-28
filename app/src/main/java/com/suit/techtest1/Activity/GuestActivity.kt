package com.suit.techtest1.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.suit.techtest1.Adapter.RecyclerGuestAdapter
import com.suit.techtest1.Helper.GridSpacingItemDecoration
import com.suit.techtest1.Model.Person
import com.suit.techtest1.Network.SearchApiDataProvider

import com.suit.techtest1.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_guest.*
import android.content.Intent

/**
 * Created by Daniel on 1/27/2018.
 */
class GuestActivity : AppCompatActivity() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        val repository = SearchApiDataProvider.provideSearchApiData()
        compositeDisposable.add(
                repository.getGuest()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe ({
                            result ->
                            refreshCollectionView(result)
                        }, { error ->
                            error.printStackTrace()
                        })
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    fun refreshCollectionView(listPerson: List<Person>) {
        recycleView.layoutManager = GridLayoutManager(this, 2)
        recycleView.addItemDecoration(GridSpacingItemDecoration(5))
        recycleView.adapter = RecyclerGuestAdapter(listPerson) {
            backToChooseActivity(it)
        }
    }

    fun backToChooseActivity(person: Person) {
        val intent = Intent(this@GuestActivity, ChooseActionActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        intent.putExtra("info", person)
        startActivity(intent)
        finish()
    }
}