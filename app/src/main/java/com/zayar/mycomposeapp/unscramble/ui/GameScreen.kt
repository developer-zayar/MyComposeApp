package com.zayar.mycomposeapp.unscramble.ui

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zayar.cupcake.CupcakeActivity
import com.zayar.mycomposeapp.R
import com.zayar.mycomposeapp.unscramble.ui.theme.UnscrambleTheme

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    val activity = (LocalActivity.current as Activity)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        Text(
            text = stringResource(R.string.unscramble_app_name),
            style = MaterialTheme.typography.titleLarge
        )

        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            wordCount = gameUiState.currentWordCount,
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            onUserGuessChanged = {
                gameViewModel.updateUserGuess(it)
            },
            onKeyboardDone = {
                gameViewModel.checkUserGuess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(R.dimen.padding_medium))
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    gameViewModel.checkUserGuess()
                }
            ) {
                Text(
                    text = stringResource(R.string.submit),
                )
            }

            OutlinedButton(
                onClick = {
                    gameViewModel.skipWord()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    fontSize = 16.sp
                )
            }

            Button(onClick = {
                Intent(activity, CupcakeActivity::class.java).also {
                    activity.startActivity(it)
                }
            }) { Text(text = "Go To Cupcake Screen") }
        }

        GameStatus(score = gameUiState.score, modifier = Modifier.padding(20.dp))

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = {
                    gameViewModel.resetGame()
                }
            )
        }
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    wordCount: Int,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium,
                color = colorScheme.onPrimary,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End)
            )
            Text(
                text = currentScrambledWord,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = stringResource(R.string.instructions),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                value = userGuess,
                onValueChange = onUserGuessChanged,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                    errorContainerColor = colorScheme.surface,
                ),
                label = {
                    if (isGuessWrong) {
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onKeyboardDone()
                    }
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val activity = (LocalContext.current as Activity)
    val activity = (LocalActivity.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
        },
        title = { Text(text = stringResource(R.string.congratulations)) },
        text = { Text(text = stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = {
                activity.finish()
            }) {
                Text(text = stringResource(R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(R.string.play_again))
            }
        },
    )

}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    UnscrambleTheme {
        GameScreen()
    }
}