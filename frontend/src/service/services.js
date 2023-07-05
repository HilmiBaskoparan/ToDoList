// Axios
import axios from "axios";

// URL
const TODO_URL = "http://localhost:4040/api/v1/todo";

// ADD
export const addTaskService = async (newTask) => {
  const response = await axios.post(TODO_URL + "/add", newTask);
  return response;
};

// DELETE
export const deleteTaskService = async (taskId) => {
  const response = await axios.delete(TODO_URL + "/delete/" + taskId);
  return response;
};

// UPDATE
export const updateTaskService = async (taskId, updateBody) => {
  const response = await axios.put(TODO_URL + "/update/" + taskId, updateBody);
  return response;
};

// LIST
export const getAllTasksService = async () => {
  const response = await axios.get(TODO_URL + "/list");
  return response;
};

// FIND BY ID
export const getByIdService = async (taskId) => {
  const response = await axios.get(TODO_URL + "/find/" + taskId);
  return response;
};

// DELETE ALL
export const deleteAllService = async () => {
  const response = await axios.delete(TODO_URL + "/delete/all");
  return response;
};

// DELETE COMPLETED TASKS
export const deleteCompletedTasksService = async () => {
  const response = await axios.delete(TODO_URL + "/delete/completed");
  return response;
};

// LIST COMPLETED TASKS
export const getCompletedTasksService = async () => {
  const response = await axios.get(TODO_URL + "/list/completed");
  return response;
};

// LIST UNCOMPLETED TASKS
export const getUncompletedTasksService = async () => {
  const response = await axios.get(TODO_URL + "/list/uncompleted");
  return response;
};