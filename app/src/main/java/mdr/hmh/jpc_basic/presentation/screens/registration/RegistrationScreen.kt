package mdr.hmh.jpc_basic.presentation.screens.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import mdr.hmh.jpc_basic.presentation.component.EventDialog
import mdr.hmh.jpc_basic.presentation.component.RoundedButton
import mdr.hmh.jpc_basic.presentation.component.SocialMediaButton
import mdr.hmh.jpc_basic.presentation.component.TransparentTextField
import mdr.hmh.jpc_basic.ui.theme.FACEBOOKCOLOR
import mdr.hmh.jpc_basic.ui.theme.GMAILCOLOR

@Composable
fun RegistrationScreen(
    state: RegisterState,
    onRegister: (String, String, String, String, String) -> Unit,
    onBack: () -> Unit,
    onDismissDialog: () -> Unit
) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val phoneNumberValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val confirmedPasswordValue = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmedPasswordVisible by remember { mutableStateOf(false) }


    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                IconButton(
                    onClick = {
                        onBack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back arrow",
                        tint = MaterialTheme.colors.primary
                    )

                }

                Text(
                    text = "Create an Account",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {


                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Name",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Email",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = phoneNumberValue,
                    textLabel = "Phone Number",
                    maxChar = 11,
                    keyboardType = KeyboardType.Phone,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "PasswordVisibility"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )

                TransparentTextField(
                    textFieldValue = confirmedPasswordValue,
                    textLabel = "Confirm Password",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()

                            onRegister(
                                nameValue.value,
                                emailValue.value,
                                phoneNumberValue.value,
                                passwordValue.value,
                                confirmedPasswordValue.value
                            )
                        }
                    ),
                    imeAction = ImeAction.Done,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmedPasswordVisible = !confirmedPasswordVisible
                            }
                        ) {
                            Icon(
                                imageVector = if (confirmedPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "PasswordVisibility"
                            )
                        }
                    },
                    visualTransformation = if (confirmedPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                RoundedButton(
                    text = "Sign Up",
                    displayProgressBar = state.displayProgressBar,
                    onClick = {
                        onRegister(
                            nameValue.value,
                            emailValue.value,
                            phoneNumberValue.value,
                            passwordValue.value,
                            confirmedPasswordValue.value
                        )
                    }
                )
                ClickableText(text = buildAnnotatedString {
                    append("Already have an account?")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Log In")
                    }
                }, onClick = {
                    onBack()
                }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Divider(modifier = Modifier.width(24.dp), thickness = 1.dp, color = Color.Gray)
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "OR", style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Black
                        )
                    )
                    Divider(modifier = Modifier.width(24.dp), thickness = 1.dp, color = Color.Gray)
                }


                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login with",
                    style = MaterialTheme.typography.body1.copy(
                        MaterialTheme.colors.primary
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SocialMediaButton(
                    text = "Login with Facebook",
                    onClick = {

                    },
                    socialMediaColor = FACEBOOKCOLOR
                )
                SocialMediaButton(
                    text = "Login with Gmail",
                    onClick = {

                    },
                    socialMediaColor = GMAILCOLOR
                )
            }


        }

        if (state.errorMessage != null) {
            EventDialog(
                errorMessage = state.errorMessage,
                onDismiss = onDismissDialog
            )
        }

    }
}