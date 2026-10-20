package sa.edu.iau.cshj.studyplanner.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import sa.edu.iau.cshj.studyplanner.ui.theme.StudyPlannerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


data class PlannerUiState(
    val greeting: String = "Hello, Student!",
    val totalTasks: Int = 7,
    val completedTasks: Int = 0
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onMarkTaskDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Study Planner",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = uiState.greeting,
            style = MaterialTheme.typography.titleLarge
        )

        Text(text = "Today's Progress")

        Text(
            text = "${uiState.completedTasks} of ${uiState.totalTasks} done"
        )

        LinearProgressIndicator(
            progress = {
                if (uiState.totalTasks > 0)
                    uiState.completedTasks.toFloat() / uiState.totalTasks
                else 0f
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onMarkTaskDone,
            enabled = uiState.completedTasks < uiState.totalTasks
        ) {
            Text("Mark Task as Done")
        }
    }
}

@Preview(showBackground = true, name = "Nothing Done")
@Composable
private fun NothingDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 0
            ),
            onMarkTaskDone = {}
        )
    }
}

@Preview(showBackground = true, name = "All Done")
@Composable
private fun AllDonePreview() {
    StudyPlannerTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(
                completedTasks = 7
            ),
            onMarkTaskDone = {}
        )
    }
}

@Composable
fun PlannerHomeRoute() {
    var uiState by remember {
        mutableStateOf(PlannerUiState())
    }

    PlannerHomeScreen(
        uiState = uiState,
        onMarkTaskDone = {
            if (uiState.completedTasks < uiState.totalTasks) {
                uiState = uiState.copy(
                    completedTasks = uiState.completedTasks + 1
                )
            }
        }
    )
}

