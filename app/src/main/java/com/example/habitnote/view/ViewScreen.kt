package com.example.habitnote.view

import android.R.attr.subtitle
import android.R.attr.textStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habitnote.model.Habit
import com.example.habitnote.ui.theme.NoteCyan
import com.example.habitnote.ui.theme.NoteDarkPurple
import com.example.habitnote.ui.theme.NoteGreen
import com.example.habitnote.ui.theme.NoteHotPink
import com.example.habitnote.ui.theme.NoteLightPurple
import com.example.habitnote.ui.theme.NoteOrange
import com.example.habitnote.ui.theme.NotePastelPink
import com.example.habitnote.ui.theme.NotePink
import com.example.habitnote.ui.theme.NoteWhite
import com.example.habitnote.ui.theme.NoteYellow
import com.example.habitnote.viewmodel.MainViewModel


@Composable
fun ViewScreen(controller: NavHostController, viewModel: MainViewModel, id : Int) {
val data = viewModel.data.value.find{
    it.id == id
}

    Scaffold(
        Modifier,
        {
            Row (
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 30.dp, vertical = 43.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null,
                    Modifier
                        .padding(end = 25.dp)
                        .size(24.dp).clickable{controller.popBackStack()},
                    tint = NoteOrange
                )



                Text(" Note", fontWeight = FontWeight.Bold, fontSize = 24.sp, color =MaterialTheme.colorScheme.onBackground)
            }

        },
        {

        }



    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 24.dp)

        ) {
            Text(data?.title ?: "", fontWeight = FontWeight.W600, fontSize = 48.sp)
            Text(data?.subtitle ?: "", fontSize = 23.sp)

        }



    }

}






