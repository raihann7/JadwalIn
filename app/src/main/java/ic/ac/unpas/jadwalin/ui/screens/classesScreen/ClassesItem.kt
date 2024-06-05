package id.ac.unpas.jadwalin.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ic.ac.unpas.jadwalin.R
import id.ac.unpas.jadwalin.models.Classes

@Composable
fun ClassesItem(item: Classes, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.lecturer_id)
        Text(modifier = Modifier.weight(3f), text = item.subject_id)
        Text(modifier = Modifier.weight(3f), text = item.name)
        Text(modifier = Modifier.weight(3f), text = item.day)
        Text(modifier = Modifier.weight(3f), text = item.star_time)
        Text(modifier = Modifier.weight(3f), text = item.end_time)
        Icon(painterResource(id = R.drawable.baseline_edit_24), "Edit", modifier = Modifier.weight(1.5f).clickable {
            onEditClick(item.id)
        })
        Icon(painterResource(id = R.drawable.baseline_delete_24), "Delete", modifier = Modifier.weight(1.5f).clickable {
            onDeleteClick(item.id)
        })
    }
}