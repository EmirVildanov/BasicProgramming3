package homework.hw1.task1.exceptions

class FileReadException(message: String, val innerException: Exception) : Exception(message)
