package ic.ac.unpas.jadwalin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, onLihat: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = " Jadwalin", modifier = Modifier.fillMaxWidth())
        Button(onClick = {
            navController.navigate(NavScreen.AddClasses.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Class")
        }

        Button(onClick = {
            navController.navigate(NavScreen.AddLecturer.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Lecture")
        }

        Button(onClick = {
            navController.navigate(NavScreen.AddSubject.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Subject")
        }
    }
}