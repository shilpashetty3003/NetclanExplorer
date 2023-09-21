package com.example.netclanexplorer.utils

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.netclanexplorer.R

sealed class BottomNavigationScreens(val route:String, @StringRes val resourceId:Int, @DrawableRes val icon:Int){
    @SuppressLint("ResourceType")
    object Explorer:BottomNavigationScreens("Explorer", R.string.Explorer, R.drawable.ic_explorer)
    object Refine:BottomNavigationScreens("Refine",R.string.Refine, R.drawable.ic_refine)
}
