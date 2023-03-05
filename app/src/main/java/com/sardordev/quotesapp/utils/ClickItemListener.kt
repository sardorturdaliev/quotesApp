package com.sardordev.quotesapp.utils

import com.sardordev.quotesapp.data.model.AllTopicsResult

interface ClickItemListener {
    fun clickItem(allTopicsResult: AllTopicsResult)

}