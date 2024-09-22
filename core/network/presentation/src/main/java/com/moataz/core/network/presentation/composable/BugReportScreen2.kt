package com.moataz.core.network.presentation.composable

//
//@Composable
//fun BugReportScreen2(
//    modifier: Modifier = Modifier,
//    viewModel: BugReportViewModel,
//    navController: NavHostController,
//) {
//    var description by remember { mutableStateOf("") }
//    val imageUri by viewModel.imageUri.observeAsState()
//
//    // Intent launcher for selecting image
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { uri: Uri? -> viewModel.setImageUri(uri) } // Set image URI in ViewModel
//    )
//
//    Column(
//        modifier = modifier.fillMaxSize().padding(16.dp)
//    ) {
//        // Text field for bug description
//        TextField(
//            value = description,
//            onValueChange = { description = it },
//            label = { Text("Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Button to pick an image
//        Button(
//            onClick = {
//                // Open image picker (gallery)
//                launcher.launch("image/*")
//            },
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Text(text = "Pick an Image")
//        }
//
//        // Display selected image (if any)
//        imageUri?.let {
//            Image(
//                painter = rememberImagePainter(data = it),
//                contentDescription = null,
//                modifier = Modifier.size(200.dp).padding(top = 8.dp)
//            )
//        }
//
//        // Button to upload the bug
//        Button(
//            onClick = { viewModel.uploadBug(description) },
//            modifier = Modifier.padding(top = 16.dp)
//        ) {
//            Text(text = "Upload Bug")
//        }
//    }
//
//    // Show upload status
//    val bugUploadStatus by viewModel.bugUploadStatus.observeAsState()
//    bugUploadStatus?.let { result ->
//        if (result.isSuccess) {
//            Toast.makeText(LocalContext.current, "Bug uploaded successfully!", Toast.LENGTH_SHORT)
//                .show()
//            navController.popBackStack() // Navigate back after successful upload
//        } else {
//            Toast.makeText(LocalContext.current, "Upload failed!", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
