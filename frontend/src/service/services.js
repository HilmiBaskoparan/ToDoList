import axios from "axios";

// ADD
export const addTaskService = async (newTask) => {
  const response = await axios.post(
    "http://localhost:4040/api/v1/todo/add",
    newTask
  );
  return response;
};

// DELETE
export const deleteTaskService = async (taskId) => {
  const response = await axios.delete(
    "http://localhost:4040/api/v1/todo/delete/" + taskId
  );
  return response;
};

// UPDATE
export const updateTaskService = async (taskId, updateBody) => {
  const response = await axios.put(
    "http://localhost:4040/api/v1/todo/update/" + taskId,
    updateBody
  );
  return response;
};

// LIST
export const getAllTasksService = async () => {
  const response = await axios.get("http://localhost:4040/api/v1/todo/list");
  return response;
};

// FIND BY ID
export const getByIdService = async (taskId) => {
  const response = await axios.get(
    "http://localhost:4040/api/v1/todo/find/" + taskId
  );
  return response;
};
