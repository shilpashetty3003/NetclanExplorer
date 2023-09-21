package com.example.netclanexplorer.component.explorer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.netclanexplorer.utils.DataClass

@Composable
fun MerchantScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {

        ConstraintLayout(modifier = Modifier.padding(bottom = 50.dp)) {
            val (searchView, column, filterIcon) = createRefs()


            LazyColumn(modifier = Modifier.constrainAs(column) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)

            }) {
                items(DataClass.merchantList) { data ->
                    CardDesign(data)
                }
            }
        }
    }
}