package ic.ac.unpas.jadwalin.ui.screens.lecturescreen

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
import kotlinx.coroutines.launch

@Composable
fun FormLectureScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<LectureViewModel>()
    val scope = rememberCoroutineScope()

    val nidn = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val fields = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Nidn") },
                modifier = Modifier.fillMaxWidth(),
                value = nidn.value, onValueChange = {
                    nidn.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Nama") },
                modifier = Modifier.fillMaxWidth(),
                value = nama.value, onValueChange = {
                    nama.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Fields") },
                modifier = Modifier.fillMaxWidth(),
                value = fields.value, onValueChange = {
                    fields.value = it
                })

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, nidn.value.text, nama.value.text, fields.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), nidn.value.text, nama.value.text, fields.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    nidn.value = TextFieldValue("")
                    nama.value = TextFieldValue("")
                    fields.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                nidn.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                fields.value = TextFieldValue("")
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
            nidn.value = TextFieldValue(it.nidn)
            nama.value = TextFieldValue(it.name)
            fields.value = TextFieldValue(it.fields)
        }
    }

}