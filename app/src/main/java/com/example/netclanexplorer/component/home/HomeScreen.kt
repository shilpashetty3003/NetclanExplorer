package com.example.netclanexplorer.component.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.netclanexplorer.R
import com.example.netclanexplorer.component.refine.RefineScreen
import com.example.netclanexplorer.utils.BottomNavigationScreens
import com.example.netclanexplorer.utils.TabScreens
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Explorer,
        BottomNavigationScreens.Refine
    )

    Scaffold(
        bottomBar = {
            BottomAppNavigation(navHostController = navController,
                items = bottomNavigationItems)
        }
    ) {
        MainScreenNavigationConfiguration(navHostController = navController)
    }
}


@Composable
fun BottomAppNavigation(
    navHostController: NavHostController,
    items: List<BottomNavigationScreens>,
) {
    androidx.compose.material.BottomNavigation(backgroundColor = Color.White) {
        val navStackBackEntry by navHostController.currentBackStackEntryAsState()
        val currentState = navStackBackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentState == item.route,
                onClick = {
                    navHostController.navigate(item.route) {
                        navHostController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }

                },
                icon = {
                    Icon(painter = painterResource(id = item.icon),
                        contentDescription = item.resourceId.toString())
                },
                label = {
                    Text(text = item.route, fontSize = 12.sp)
                },
                selectedContentColor = Color.Black, unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
            )
        }

    }

}


@Composable
fun MainScreenNavigationConfiguration(navHostController: NavHostController) {

    NavHost(navController = navHostController,
        startDestination = BottomNavigationScreens.Explorer.route) {
        composable(BottomNavigationScreens.Explorer.route) {
            TabbedScreen()
        }
        composable(BottomNavigationScreens.Refine.route) {
            RefineScreen()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabbedScreen() {
    val tabs = listOf(TabScreens.Personal, TabScreens.Business, TabScreens.Merchant)
    val pageState = rememberPagerState()
    Scaffold(topBar = {
        TopBar()
    }) {
        Column(modifier = Modifier.padding(it)) {
            Tabs(tabs = tabs, pagerState = pageState)
            TabsContent(tabs = tabs, pagerState = pageState)

        }

    }
}


@Composable
fun TopBar() {

    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(color = colorResource(
            id = R.color.green700))) {
        val (menu, name, location, locationIcon) = createRefs()
        Icon(painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(menu) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .width(70.dp)
                .height(70.dp)
                .padding(5.dp), tint = Color.White)

        Text(text = stringResource(id = R.string.name), color = Color.White, modifier = Modifier
            .constrainAs(name) {
                start.linkTo(menu.end, 10.dp)
            }
            .padding(top = 5.dp), fontSize = 15.sp, style = MaterialTheme.typography.body1)
        Icon(painter = painterResource(id = R.drawable.ic_baseline_location_on_24),
            contentDescription = "",
            modifier = Modifier.constrainAs(locationIcon) {
                top.linkTo(name.bottom)
                start.linkTo(name.start)
            }, tint = Color.White)

        Text(text = stringResource(id = R.string.location),
            modifier = Modifier.constrainAs(location) {
                top.linkTo(name.bottom)
                start.linkTo(locationIcon.end)
            },
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabScreens>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.secondary,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {

        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = { Text(text = tab.title, fontSize = 15.sp) }
            )
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabScreens>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultHomeScreen() {
    HomeScreen()
}