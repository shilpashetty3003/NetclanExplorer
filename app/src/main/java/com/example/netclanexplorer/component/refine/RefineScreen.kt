package com.example.netclanexplorer.component.refine

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.netclanexplorer.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RefineScreen() {

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(topBar = {
            TopRefineAppBar()
        }) {
            RefineContent()
        }
    }
}

@Composable
fun RefineContent() {

    LazyColumn(state = rememberLazyListState()) {
        item { SearchAvailability() }
        item { AddStatus() }
        item { LocalDistance() }
        item { Purpose() }
        item { Save() }

    }

}


@Composable
fun TopRefineAppBar() {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(color = colorResource(id = R.color.green200))) {
        Text(text = "Refine",
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 40.dp, top = 10.dp, bottom = 10.dp))
    }
}

@Composable
fun SearchAvailability() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val dropDownList = listOf(stringResource(id = R.string.item1),
        stringResource(id = R.string.item2),
        stringResource(id = R.string.item3),
        stringResource(id = R.string.item4))
    var label by remember {
        mutableStateOf("Available | Hey Let Us Connect")
    }


    Column() {
        Text(text = "Select Your Availability",
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
            fontFamily = FontFamily.SansSerif)

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)

        ) {
            Button(onClick = { isExpanded = !isExpanded },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White, contentColor = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp,
                        colorResource(id = R.color.green200),
                        shape = RoundedCornerShape(10.dp))
            ) {

                Text(text = label, modifier = Modifier.fillMaxWidth(0.8f))
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )

            }

            DropdownMenu(
                expanded = isExpanded,
                modifier = Modifier.fillMaxWidth(0.9f),
                onDismissRequest = { isExpanded = false },
            ) {
                dropDownList.forEach { onClickLabel ->
                    DropdownMenuItem(onClick = {
                        isExpanded = false
                        label = onClickLabel


                    }) {
                        Text(text = onClickLabel,
                            color = Color.Black,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp))
                    }
                }
            }
        }

    }
}

@Composable
fun AddStatus() {

    var status by remember {
        mutableStateOf("Hi community! I am open to new connections \"\uD83D\uDE0A\"")
    }
    val maxChar = 250
    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        Text(text = "Add Your Status",
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif)

        TextField(value = status,
            onValueChange = {
                status = it
            }, shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .border(0.5.dp,
                    colorResource(id = R.color.green200),
                    shape = RoundedCornerShape(10.dp)), colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ))
        Text(
            text = "${status.length} / $maxChar",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        )
    }
}


@Composable
fun LocalDistance() {

    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        Text(text = "Select Hyper Local Distance ",
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif)
        SliderWithLabel()

    }

}

@Composable
fun SliderWithLabel() {
    var sliderValue by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
//        Text(
//            text = "Value: %.2f".format(sliderValue),
//            style = MaterialTheme.typography.body1,
//        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Slider(
                value = sliderValue,
                onValueChange = { newValue ->
                    sliderValue = newValue
                    Log.d("TAG", "SliderWithLabel:  $sliderValue")
                },
                valueRange = 1f..100f,
                steps = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "1 Km",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
            Text(
                text = "${sliderValue.toInt()} Km",
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Right
            )
        }

    }
}

@Composable
fun Purpose() {

    var isCoffeeSelected = remember {
        mutableStateOf(false)
    }
    var isBusinessSelected = remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        Text(text = "Select Purpose",
            color = MaterialTheme.colors.primaryVariant,
            fontSize = 15.sp,
            fontFamily = FontFamily.SansSerif)

        Row(modifier = Modifier.padding(8.dp)) {
            Chips(text = "Coffee", isSelected = isCoffeeSelected.value) {
                isCoffeeSelected.value = it
            }
            Chips(text = "Business", isSelected = isBusinessSelected.value) {
                isBusinessSelected.value = it
            }
//            Chips(text = "Hobbies", isSelected = isHobbiesSelected.value) {
//                isHobbiesSelected.value = it
//            }

        }
    }
}

@Composable
fun Chips(isSelected: Boolean = false, text: String, onValueChange: (isSelected: Boolean) -> Unit) {

    Button(onClick = {
        if (isSelected) {
            onValueChange(false)
        } else {
            onValueChange(true)
        }
    }, colors = ButtonDefaults.buttonColors(
        backgroundColor = if (isSelected) MaterialTheme.colors.secondary else Color.White,
        contentColor = if (isSelected) Color.White else MaterialTheme.colors.secondary
    ), modifier = Modifier
        .padding(8.dp)
    ) {
        Text(text = text)
    }
}


@Composable
fun Save() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 100.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                // Handle button click here
            },
        ) {
            Text(text = "Save & Explore", color = Color.White)
        }

        Button(onClick = { }) {

        }

    }
}

@Composable
fun Ads() {

}