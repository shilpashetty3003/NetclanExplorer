package com.example.netclanexplorer.component.explorer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.netclanexplorer.R
import com.example.netclanexplorer.ui.theme.Teal200
import com.example.netclanexplorer.utils.DataClass

@Composable
fun PersonalScreen() {

    Surface(modifier = Modifier.fillMaxSize()) {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        ConstraintLayout(modifier = Modifier.padding(bottom = 150.dp)) {
            val (searchView, column, filterIcon) = createRefs()
            SearchView(modifier = Modifier.constrainAs(searchView) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_filter_alt_24),
                contentDescription = "", modifier = Modifier.constrainAs(filterIcon) {
                    start.linkTo(searchView.end,)
                    top.linkTo(searchView.top)
                    bottom.linkTo(searchView.bottom)
                }
            )
//

            LazyColumn(modifier = Modifier.constrainAs(column) {
                top.linkTo(searchView.bottom)
                start.linkTo(parent.start)

            }) {
                items(DataClass.personalList) { data ->
                    CardDesign(data)
                }
            }
        }
    }
}

