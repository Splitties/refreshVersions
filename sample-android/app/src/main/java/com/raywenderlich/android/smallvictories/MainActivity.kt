/*
 *
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.smallvictories

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModel = ViewModelProviders.of(this).get(VictoryViewModel::class.java)
        viewModel.viewState.observe(this, Observer { it ->
            it?.let { render(it) }
        })
        viewModel.repository = Repository(this)
        viewModel.initialize()

        fab.setOnClickListener {
            viewModel.incrementVictoryCount()
        }
        textVictoryTitle.setOnClickListener { showVictoryTitleDialog(viewModel) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                // TODO reset existing victory title and count
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun render(uiModel: VictoryUiModel) {
        when (uiModel) {
            is VictoryUiModel.TitleUpdated -> {
                textVictoryTitle.text = uiModel.title
            }
            is VictoryUiModel.CountUpdated -> {
                textVictoryCount.text = uiModel.count.toString()
            }
        }
    }

    private fun showVictoryTitleDialog(viewModel: VictoryViewModel) {
        AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.dialog_title))

            val input = EditText(this@MainActivity)
            input.setText(textVictoryTitle.text)
            val density = Resources.getSystem().displayMetrics.density
            val padding = Math.round(16 * density)

            val layout = FrameLayout(context)
            layout.setPadding(padding, 0, padding, 0)
            layout.addView(input)

            setView(layout)

            setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                viewModel.setVictoryTitle(input.text.toString())
            }
            setNegativeButton(getString(R.string.dialog_cancel), null)
            create().show()
        }
    }
}
