package com.example.netclanexplorer.utils

import androidx.compose.runtime.Composable
import com.example.netclanexplorer.component.explorer.BusinessScreen
import com.example.netclanexplorer.component.explorer.MerchantScreen
import com.example.netclanexplorer.component.explorer.PersonalScreen


typealias ComposableFun = @Composable () -> Unit

sealed class TabScreens(var title: String, var screen: ComposableFun) {
    object Personal : TabScreens("Personal", {
        PersonalScreen()
    })

    object Business : TabScreens("Business", {
        BusinessScreen()
    })

    object Merchant : TabScreens("Merchant", {
        MerchantScreen()
    })
}
