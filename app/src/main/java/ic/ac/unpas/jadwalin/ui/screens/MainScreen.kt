package ic.ac.unpas.jadwalin.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ic.ac.unpas.jadwalin.R
import ic.ac.unpas.jadwalin.ui.screens.FormClassesScreen.FormClassesScreen
import ic.ac.unpas.jadwalin.ui.screens.classesScreen.ListClassesScreen
import ic.ac.unpas.jadwalin.ui.screens.lecturescreen.FormLectureScreen
import ic.ac.unpas.jadwalin.ui.screens.lecturescreen.ListLecturerScreen
import ic.ac.unpas.jadwalin.ui.screens.subjectScreen.FormSubjectScreen
import ic.ac.unpas.jadwalin.ui.screens.subjectScreen.ListSubjectScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onExitClick: () -> Unit) {
    val navController = rememberNavController()
    val currentRoute = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
                 TopAppBar(
                     title = { Text(text = "JadwalIn") },
                     navigationIcon = {
                         if (currentRoute.value != NavScreen.Login.route) {
                             Image(
                                 painterResource(id = R.drawable.baseline_home_24),
                                 contentDescription = "Menu",
                                 colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                                 modifier = Modifier.clickable {
                                     navController.navigate(NavScreen.Home.route)
                                 }
                             )
                         }

                     },
                     colors = topAppBarColors(
                         containerColor = MaterialTheme.colorScheme.primary,
                         titleContentColor = MaterialTheme.colorScheme.onPrimary
                     ),
                     actions = {
                         Image(
                             painterResource(id = R.drawable.baseline_exit_to_app_24),
                             contentDescription = "Exit",
                             colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                             modifier = Modifier.clickable {
                                 onExitClick()
                             }
                         )
                     }
                 )
        },
        bottomBar = {
            if (currentRoute.value != NavScreen.Login.route) {
                BottomAppBar (
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Lecturer",
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.clickable { navController.navigate(NavScreen.ListLecturer.route) }
                        )
                        Text(
                            text = "Classes",
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.clickable { navController.navigate(NavScreen.ListClasses.route) }
                        )
                        Text(
                            text = "Subject",
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.clickable { navController.navigate(NavScreen.ListSubject.route) }
                        )


                    }
                }
            }

        },

    ) { innerPadding ->
        NavHost(navController = navController, startDestination = NavScreen.Login.route) {

            composable(NavScreen.Login.route) {
                currentRoute.value = NavScreen.Login.route
                LoginScreen(modifier = Modifier.padding(innerPadding)) {
                    navController.navigate(NavScreen.Home.route)
                }
            }
            composable(NavScreen.Home.route) {
                currentRoute.value = NavScreen.Home.route
                HomeScreen(navController = navController, modifier = Modifier.padding(innerPadding)) {
                    navController.navigate(NavScreen.ListClasses.route)
                }
            }
            composable(NavScreen.AddClasses.route) {
                currentRoute.value = NavScreen.AddClasses.route
                FormClassesScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(NavScreen.AddLecturer.route) {
                currentRoute.value = NavScreen.AddClasses.route
                FormLectureScreen(modifier = Modifier.padding(innerPadding))
            }
            composable(NavScreen.AddSubject.route) {
                currentRoute.value = NavScreen.AddClasses.route
                FormSubjectScreen(modifier = Modifier.padding(innerPadding))
            }


            composable(NavScreen.ListClasses.route) {
                currentRoute.value = NavScreen.ListClasses.route
                ListClassesScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditClasses.route}/$id")
                }
            }
            composable(NavScreen.ListSubject.route) {
                currentRoute.value = NavScreen.ListSubject.route
                ListSubjectScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditSubject.route}/$id")
                }
            }
            composable(NavScreen.ListLecturer.route) {
                currentRoute.value = NavScreen.ListLecturer.route
                ListLecturerScreen(modifier = Modifier.padding(innerPadding)) { id ->
                    navController.navigate("${NavScreen.EditLecturer.route}/$id")
                }
            }

            composable(NavScreen.EditClasses.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditClasses.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditClasses.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditClasses.route
                FormClassesScreen(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.EditLecturer.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditLecturer.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditLecturer.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditLecturer.route
                FormLectureScreen(modifier = Modifier.padding(innerPadding), id = id)
            }

            composable(NavScreen.EditSubject.routeWithArgument,
                arguments = listOf(navArgument(NavScreen.EditSubject.argument0) { type = NavType.StringType }))
            { backStackEntry ->
                val id = backStackEntry.arguments?.getString(NavScreen.EditSubject.argument0) ?: return@composable

                currentRoute.value = NavScreen.EditSubject.route
                FormSubjectScreen(modifier = Modifier.padding(innerPadding), id = id)
            }
        }
    }
}