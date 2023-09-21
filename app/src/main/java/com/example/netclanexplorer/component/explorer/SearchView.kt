package com.example.netclanexplorer.component.explorer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.netclanexplorer.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },

        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(100.dp)
            .padding(20.dp)
            .border(0.2.dp, Color.Black, RoundedCornerShape(50.dp)),
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(3.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(3.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        placeholder = {
            Text(text = "Search ", color = Color.Black.copy(0.5f))
        },
        singleLine = true,
        shape = RoundedCornerShape(30.dp), // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SearchView(modifier: Modifier) {

    Box(modifier = modifier
        .padding(20.dp)
        .fillMaxWidth(0.85f)
        .height(40.dp)
        .border(width = 0.5.dp,
            color = colorResource(id = R.color.green200).copy(0.3f),
            shape = RoundedCornerShape(30.dp))) {
        var value by remember {
            mutableStateOf("")
        }

        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (searchIcon, searchInput, closeIcon) = createRefs()
            Icon(
                Icons.Default.Search, tint = colorResource(id = R.color.green200).copy(0.3f),
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(searchIcon) {
                        top.linkTo(parent.top, 5.dp)
                        start.linkTo(parent.start, 20.dp)
                        bottom.linkTo(parent.bottom, 5.dp)
                    }.padding(3.dp)
                    .size(24.dp)
            )

            BasicTextField(modifier = Modifier.constrainAs(searchInput) {
                top.linkTo(parent.top,)
                start.linkTo(searchIcon.end, 20.dp)
                bottom.linkTo(parent.bottom, )


            }, value = value, onValueChange = {
                value = it
            }, singleLine = true, textStyle = TextStyle(
                color = colorResource(id = R.color.green200).copy(0.3f), fontSize = 20.sp

            ))
            if (value != "") {
                IconButton(
                    modifier = Modifier
                        .constrainAs(closeIcon) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        },
                    onClick = {
                        value = ""
                    },
                ) {
                    Icon(
                        Icons.Default.Close, tint = colorResource(id = R.color.green200).copy(0.3f),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(closeIcon) {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                            .padding(3.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    }

}

