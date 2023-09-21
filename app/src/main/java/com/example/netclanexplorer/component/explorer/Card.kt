package com.example.netclanexplorer.component.explorer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.netclanexplorer.R
import com.example.netclanexplorer.utils.Data

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardDesign(data: Data) {
    ConstraintLayout() {
        val (card, name, location, hobbies, shortDes, longDes, nameCard, message, phone, inviteText, distance, distanceMeasure) = createRefs()

        Card(onClick = {},
            elevation = 10.dp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 35.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)

                }) {

            ConstraintLayout() {
                Invite(modifier = Modifier.constrainAs(inviteText) {
                    top.linkTo(parent.top, 15.dp)
                    end.linkTo(parent.end, 10.dp)
                })
                Name(data, modifier = Modifier.constrainAs(name) {
                    top.linkTo(inviteText.bottom, 10.dp)
                    start.linkTo(parent.start, 80.dp)
                })
                Location(data, modifier = Modifier.constrainAs(location) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start, 80.dp)
                })
                Distance(data, modifier = Modifier.constrainAs(distance) {
                    top.linkTo(location.bottom)
                    start.linkTo(parent.start, 80.dp)
                })
                HorizontalDistance(modifier = Modifier.constrainAs(distanceMeasure) {
                    top.linkTo(distance.bottom, 10.dp)
                    start.linkTo(parent.start, 80.dp)
                })
                Hobbies(data = data, modifier = Modifier.constrainAs(hobbies) {
                    top.linkTo(distanceMeasure.bottom, 20.dp)
                    start.linkTo(parent.start, 40.dp)
                })
                ShortDes(data = data, modifier = Modifier
                    .constrainAs(shortDes) {
                        top.linkTo(hobbies.bottom, 5.dp)
                        start.linkTo(hobbies.start)
                    }
                    .fillMaxWidth(0.9f))


                LongDes(data = data, modifier = Modifier
                    .constrainAs(longDes) {
                        top.linkTo(shortDes.bottom, 5.dp)
                        start.linkTo(shortDes.start)
                        bottom.linkTo(parent.bottom, 10.dp)
                    }
                    .fillMaxWidth(0.85f))


            }
        }
        Card(modifier = Modifier
            .constrainAs(nameCard) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start, 10.dp)
            }
            .width(70.dp)
            .height(80.dp),
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp)) {
            Image(painter = painterResource(id = data.icon),
                contentDescription = "",
                contentScale = ContentScale.FillBounds)

        }
    }
}

@Composable
fun Name(data: Data, modifier: Modifier) {
    Text(text = data.name,
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        fontFamily = FontFamily.SansSerif,
        color = colorResource(id = R.color.green700))
}

@Composable
fun Invite(modifier: Modifier) {
    Text(text = "+ INVITE",
        style = MaterialTheme.typography.body1,
        fontFamily = FontFamily.SansSerif,
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier)
}

@Composable
fun Location(data: Data, modifier: Modifier) {
    Text(text = data.location,
        modifier = modifier,
        fontFamily = FontFamily.SansSerif,
        style = MaterialTheme.typography.body1,
        color = colorResource(id = R.color.green200).copy(0.8f))
}

@Composable
fun Distance(data: Data, modifier: Modifier) {
    Text(text = "Within ${data.distance} m",
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primaryVariant,
        modifier = modifier)
}

@Composable
fun HorizontalDistance(modifier: Modifier) {
    Box(modifier = modifier
        .width(120.dp)
        .height(15.dp)
        .background(Color.Transparent, shape = RoundedCornerShape(10.dp))) {
        Row() {
            Box(modifier = Modifier
                .width(40.dp)
                .height(15.dp)
                .background(colorResource(id = R.color.grey200),
                    RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))) {
            }
            Box(modifier = Modifier
                .width(80.dp)
                .height(15.dp)
                .background(colorResource(id = R.color.grey700),
                    RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))) {

            }
        }
    }
}

@Composable
fun Hobbies(data: Data, modifier: Modifier) {
    Text(text = data.hobbies,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center,
        modifier = modifier)
}

@Composable
fun ShortDes(data: Data, modifier: Modifier) {
    Text(text = stringResource(id = data.shortDes),
        style = MaterialTheme.typography.body1,
        color = colorResource(id = R.color.green200).copy(0.8f),
        modifier = modifier)
}

@Composable
fun LongDes(data: Data, modifier: Modifier) {
    Text(text = stringResource(id = data.longDes),
        style = MaterialTheme.typography.body1,
        color = colorResource(id = R.color.green200).copy(0.8f),
        modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun CardDesignPreview() {
    CardDesign(data = Data(R.drawable.actor9,
        "Shilpa Shetty",
        "Mumbai | HR",
        "100-200",
        "Business | Coffee",
        R.string.short_description,
        R.string.long_description))
}