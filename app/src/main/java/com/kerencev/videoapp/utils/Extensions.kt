package com.kerencev.videoapp.utils

import android.content.res.AssetManager

/**
 * Function to read Json file
 * @return Json file to String
 */
fun AssetManager.readAssetsFile(fileName: String): String =
    open(fileName).bufferedReader().use { it.readText() }