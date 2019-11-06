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

import android.content.Context

open class Repository(context: Context) : VictoryRepository {
    companion object {

        private const val PACKAGE_NAME = "com.raywenderlich.android.smallvictories"
        private const val KEY_VICTORY_TITLE = "victory_title"
        private const val KEY_VICTORY_COUNT = "victory_count"
    }

    private val sharedPreferences = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE)

    override fun getVictoryTitleAndCount(): Pair<String, Int> {
        return Pair(getVictoryTitle(), getVictoryCount())
    }

    override fun setVictoryTitle(title: String) {
        sharedPreferences.edit().putString(KEY_VICTORY_TITLE, title).apply()
    }

    override fun getVictoryTitle(): String {
        return sharedPreferences.getString(KEY_VICTORY_TITLE, "Victory title")!!
    }

    override fun setVictoryCount(count: Int) {
        sharedPreferences.edit().putInt(KEY_VICTORY_COUNT, count).apply()
    }

    override fun getVictoryCount(): Int {
        return sharedPreferences.getInt(KEY_VICTORY_COUNT, 0)
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
