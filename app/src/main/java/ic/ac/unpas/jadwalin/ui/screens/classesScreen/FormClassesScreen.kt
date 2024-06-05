package ic.ac.unpas.jadwalin.ui.screens.FormClassesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import ic.ac.unpas.jadwalin.ui.screens.ClassesViewModel.ClassesViewModel
import kotlinx.coroutines.launch

@Composable
fun FormClassesScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<ClassesViewModel>()
    val scope = rememberCoroutineScope()

    val lecturer_id = remember { mutableStateOf(TextFieldValue("")) }
    val subject_id = remember { mutableStateOf(TextFieldValue("")) }
    val name = remember { mutableStateOf(TextFieldValue("")) }
    val day = remember { mutableStateOf(TextFieldValue("")) }
    val start_time = remember { mutableStateOf(TextFieldValue("")) }
    val end_time = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Lecture") },
                modifier = Modifier.fillMaxWidth(),
                value = lecturer_id.value, onValueChange = {
                    lecturer_id.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Subject") },
                modifier = Modifier.fillMaxWidth(),
                value = subject_id.value, onValueChange = {
                    subject_id.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth(),
                value = name.value, onValueChange = {
                    name.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Day") },
                modifier = Modifier.fillMaxWidth(),
                value = day.value, onValueChange = {
                    day.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Start Time") },
                modifier = Modifier.fillMaxWidth(),
                value = start_time.value, onValueChange = {
                    start_time.value = it
                })

            OutlinedTextField(
                label = { Text(text = "End Time") },
                modifier = Modifier.fillMaxWidth(),
                value = end_time.value, onValueChange = {
                    end_time.value = it
                })

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, lecturer_id.value.text, subject_id.value.text, name.value.text, day.value.text, start_time.value.text, end_time.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), lecturer_id.value.text, subject_id.value.text, name.value.text, day.value.text, start_time.value.text, end_time.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    lecturer_id.value = TextFieldValue("")
                    subject_id.value = TextFieldValue("")
                    name.value = TextFieldValue("")
                    day.value = TextFieldValue("")
                    start_time.value = TextFieldValue("")
                    end_time.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                lecturer_id.value = TextFieldValue("")
                subject_id.value = TextFieldValue("")
                name.value = TextFieldValue("")
                day.value = TextFieldValue("")
                start_time.value = TextFieldValue("")
                end_time.value = TextFieldValue("")
            }
        }

        LaunchedEffect(id) {
            if (id != null) {
                scope.launch {
                    viewModel.find(id)
                }
            }
        }

        viewModel.item.observe(LocalLifecycleOwner.current) {
            lecturer_id.value = TextFieldValue(it.lecturer_id)
            subject_id.value = TextFieldValue(it.subject_id)
            name.value = TextFieldValue(it.name)
            day.value = TextFieldValue(it.day)
            start_time.value = TextFieldValue(it.star_time)
            end_time.value = TextFieldValue(it.end_time)
        }
    }

}