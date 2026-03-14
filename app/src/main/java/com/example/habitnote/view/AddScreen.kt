package com.example.habitnote.view

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
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun AddScreen(controller: NavHostController, viewModel: MainViewModel) {

    var showColorMenu by remember{ mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }

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



                Text("Add Note", fontWeight = FontWeight.Bold, fontSize = 24.sp, color =MaterialTheme.colorScheme.onBackground)

                Spacer(Modifier.weight(1f))

                OutlinedButton(
                    onClick = {
                       viewModel.addHabit(
                           Habit(
                               id = viewModel.data.value.size + 1,
                               title = title,
                               subtitle = subtitle,
                               color = viewModel.addColor
                           )
                       )
                           title = ""
                           subtitle = ""
                           controller.popBackStack()

                    }

                ) {

                    Text("Save")
                }

                Icon(
                    Icons.Rounded.MoreVert,
                    contentDescription = null,
                    Modifier.size(24.dp).clickable{
                        showColorMenu = true
                    },
                )
                DropdownMenu(
                    expanded = showColorMenu,
                    onDismissRequest = { showColorMenu = false },
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    ColorPicker(
                        { /*TODO*/ },
                        {color -> viewModel.addColor = color},
                        viewModel.addColor
                    )


                }

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
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = {
                    Text("Tittle", fontWeight = FontWeight.W600, fontSize = 48.sp)
                },
                textStyle = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.W600),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(40.dp))
            OutlinedTextField(
                value = subtitle,
                onValueChange = { subtitle = it },
                placeholder = {
                    Text("Type something...", fontSize = 23.sp)
                },
                textStyle = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.W400),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
            ))







        }



    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    onDelete: (Int) -> Unit,
    onColorSelected: (Color) -> Unit,
    selectionColor: Color = NoteWhite
) {
    val colors = listOf(
        NoteWhite, NotePink, NoteOrange,
        NoteYellow, NoteGreen, NoteCyan,
        NoteHotPink, NoteLightPurple, NoteDarkPurple,
        NotePastelPink
    )


    Column(Modifier.padding(16.dp)) {
        Text(
            "Select Color",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )
        val chunkedColors = colors.chunked(5)
        chunkedColors.forEach { row ->
            Row(
                Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { color ->
                    ColorCircle(
                        color = color,
                        isSelected = color == selectionColor,
                        onClick = { onColorSelected(color) }
                    )
                }
            }
        }
    }
}

    @Composable
    fun ColorCircle(
        color: Color,
        isSelected:Boolean,
        onClick : () -> Unit
    ){
        Box(
            Modifier.size(40.dp).clip(CircleShape).background(color)
                .border(
                    width = if(isSelected) 2.dp else 1.dp,
                    color = if(isSelected) Color.Black else Color.LightGray,
                    shape = CircleShape
                )
                .clickable{onClick()}
                )
    }



















