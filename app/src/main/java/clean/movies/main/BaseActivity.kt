package clean.movies.main

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInject()
    }

    private fun doInject() {
        AndroidInjection.inject(this)
    }
}