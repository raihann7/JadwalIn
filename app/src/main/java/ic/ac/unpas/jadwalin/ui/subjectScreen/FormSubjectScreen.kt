package ic.ac.unpas.jadwalin.ui.subjectScreen

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
import javax.security.auth.Subject

@Composable
fun FormLectureScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<SubjectViewModel>()
    val scope = rememberCoroutineScope()

    val code = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val decription = remember { mutableStateOf(TextFieldValue("")) }
    val sks = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Code") },
                modifier = Modifier.fillMaxWidth(),
                value = code.value, onValueChange = {
                    code.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Nama") },
                modifier = Modifier.fillMaxWidth(),
                value = nama.value, onValueChange = {
                    nama.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Decription") },
                modifier = Modifier.fillMaxWidth(),
                value = decription.value, onValueChange = {
                    decription.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Sks") },
                modifier = Modifier.fillMaxWidth(),
                value = sks.value, onValueChange = {
                    sks.value = it
                })

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, code.value.text, nama.value.text, decription.value.text, sks.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), code.value.text, nama.value.text, decription.value.text, sks.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    code.value = TextFieldValue("")
                    nama.value = TextFieldValue("")
                    decription.value = TextFieldValue("")
                    sks.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                code.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                decription.value = TextFieldValue("")
                sks.value = TextFieldValue("")
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
            code.value = TextFieldValue(it.code)
            nama.value = TextFieldValue(it.nama)
            decription.value = TextFieldValue(it.decription)
            sks.value = TextFieldValue(it.sks)
        }
    }

}