val inputStream = context.resources.openRawResource(R.raw.champions)
val jsonString = inputStream.bufferedReader().use { it.readText() }