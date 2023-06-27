import { useEffect, useState } from "react";
// Popup Modal
import Popup from "../component/Popup";
// SERVICES
import {
  getAllTasksService,
  addTaskService,
  deleteTaskService,
  updateTaskService,
  getCompletedTasksService,
  getUncompletedTasksService,
  deleteAllService,
  deleteCompletedTasksService,
} from "../service/services";

function HomePage() {
  const [allItems, setAllItems] = useState([]);
  const [newTaskInput, setNewTaskInput] = useState("");
  const [isOpen, setIsOpen] = useState(false);
  const [currentTodo, setCurrentTodo] = useState({ id: 0, description: "" });

  // GET TODO LIST
  useEffect(() => {
    const dataList = async () => {
      let response = await getAllTasksService();
      setAllItems(response.data);
    };
    dataList();
  }, []);

  // ADD NEW TASK
  const addNewTask = async () => {
    await addTaskService({ description: newTaskInput })
      .then((response) => {
        const newAllItems = [...allItems];
        newAllItems.push(response.data);
        setAllItems(newAllItems);
      })
      .catch((error) => {
        window.alert(error.response.data.message);
      });
  };

  // DELETE TASK
  const deleteTask = async (task) => {
    await deleteTaskService(task?.id)
      .then((response) => {
        const newList = allItems.filter(
          (task) => task?.id !== response?.data?.id
        );
        setAllItems(newList);
      })
      .catch((error) => {
        window.alert(error.response.data.message);
      });
  };

  // UPDATE TASK
  const updateDescription = async () => {
    await updateTaskService(currentTodo?.id, {
      description: currentTodo?.description,
    })
      .then((response) => {
        const newList = allItems?.map((variable) => {
          if (variable?.id === response?.data?.id) {
            return { ...variable, description: response?.data?.description };
          }
          return variable;
        });
        setAllItems(newList);
      })
      .catch((error) => {
        window.alert(error.response.data.message);
      });
    setIsOpen(false);
  };

  // Open Popup
  const openPopup = (todo) => {
    setIsOpen(true);
    setCurrentTodo({
      ...currentTodo,
      id: todo?.id,
      description: todo?.description,
    });
  };
  // Close Popup
  const closePopup = () => {
    setIsOpen(false);
  };

  // Update Todo Task When Checked
  const updateIsDone = async (task) => {
    await updateTaskService(task?.id, { isDone: !task?.isDone })
      .then((response) => {
        const newList = allItems?.map((variable) => {
          if (variable?.id === response?.data?.id) {
            return { ...variable, isDone: response?.data?.isDone };
          }
          return variable;
        });
        setAllItems(newList);
      })
      .catch((error) => {
        console.log(error.response.data.message);
      });
  };

  // FILTER LIST (All, Completed, Uncompleted)
  const getAllTasks = async () => {
    let response = await getAllTasksService();
    setAllItems(response.data);
  };
  const getCompletedTasks = async () => {
    let response = await getCompletedTasksService();
    setAllItems(response.data);
  };
  const getUncompletedTasks = async () => {
    let response = await getUncompletedTasksService();
    setAllItems(response.data);
  };

  // DELETE ALL TASKS
  const deleteAllTasks = async () => {
    await deleteAllService()
      .then((response) => {
        setAllItems([]);
      })
      .catch((error) => {
        window.alert(error.response.data.message);
      });
  };

  // DELETE COMPLEETED TASKS
  const deleteCompletedTasks = async () => {
    await deleteCompletedTasksService()
      .then((response) => {
        const newList = allItems.filter((element) => element?.isDone === false);
        setAllItems(newList);
      })
      .catch((error) => {
        window.alert(error.response.data.message);
      });
  };

  return (
    <>
      <div className="container m-3 p-2 rounded mx-auto bg-light shadow border">
        {/* TITLE */}
        <div className="row mt-1 pt-2">
          <div className="col">
            <div className="p-0 h1 text-center mx-auto display-inline-block">
              <h1>TodoInput</h1>
            </div>
          </div>
        </div>

        {/* ADD NEW TASK */}
        <div className="row m-0 p-3">
          <div className="col col-11 mx-auto">
            <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
              <div className="col">
                <input
                  type="text"
                  className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                  placeholder="New Todo"
                  onChange={(value) => setNewTaskInput(value.target.value)}
                />
              </div>
              <div className="col-auto px-0 mx-0 mr-2">
                <button
                  type="button"
                  className="btn btn-primary btn-lg btn-block"
                  onClick={addNewTask}
                >
                  Add New Task
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="container m-4 p-2 rounded mx-auto bg-light shadow border">
        {/* FILTERING OF LIST OPTIONS */}
        <div className="row mt-2 p-3 px-5 justify-content-end">
          <div class="d-flex justify-content-center">
            <h2>TodoList</h2>
          </div>
          <div className="row">
            <button
              className="btn btn-primary btn-md col m-3"
              onClick={getAllTasks}
            >
              All
            </button>
            <button
              className="btn btn-primary btn-md col m-3"
              onClick={getCompletedTasks}
            >
              Completed
            </button>
            <button
              className="btn btn-primary btn-md col m-3"
              onClick={getUncompletedTasks}
            >
              InCompleted
            </button>
          </div>
        </div>

        {/* SHOW DATA LIST */}
        {allItems?.map((task) => (
          <div className="row mx-1 px-5 pb-3 w-80">
            <div className="col mx-auto">
              <div className="row px-3 align-items-center todo-item rounded border">
                <div className="col px-1 m-1 d-flex align-items-center">
                  <input
                    style={
                      task?.isDone
                        ? { textDecoration: "line-through", color: "red" }
                        : { textDecoration: "none" }
                    }
                    type="text"
                    className="form-control border-0 edit-todo-input bg-transparent rounded px-3 text-x1"
                    title="Todo Item #1"
                    readOnly
                    disabled
                    value={task?.description}
                  />
                </div>

                <div className="col-auto m-1 p-0 px-3 d-none"></div>

                <div className="col-auto m-1 p-0">
                  <div className="col d-flex align-items-center justify-content-end">
                    {/* CHECKBOX */}
                    <h3 className="m-1 p-0">
                      <i
                        className="fa fa-square-o text-primary btn mb-0 p-0 d-none"
                        data-toogle="tooltip"
                        data-placement="bottom"
                        title="Mark as Complete"
                      ></i>
                      <input
                        type="checkbox"
                        className="form-control-md form-check-input mb-1"
                        checked={task.isDone}
                        onChange={() => updateIsDone(task)}
                      ></input>
                    </h3>
                    {/* EDIT BUTTON */}
                    <h5 className="m-0 p-0 px-2">
                      <i
                        className="fa-solid fa-pen fa-lg"
                        style={{ color: "#ffd600" }}
                        onClick={() => openPopup(task)}
                      ></i>
                    </h5>
                    {/* DELETE BUTTON */}
                    <h5 className="m-0 p-0 px-2">
                      <i
                        className="fa-solid fa-trash fa-lg"
                        style={{ color: "#d50000" }}
                        onClick={() => deleteTask(task)}
                      ></i>
                    </h5>
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))}

        {/* POPUP MODAL */}
        <div>
          {isOpen && (
            <Popup
              content={
                <>
                  <div className="container">
                    <b className="text-primary">UPDATE YOUR TODO</b>
                    <div className="row px-3 align-items-center todo-item rounded border mt-4 mb-4">
                      <textarea
                        type="input"
                        rows="2"
                        className="form-control border-0 edit-todo-input bg-transparent rounded px-3 text-x1"
                        placeholder="Please write your todo description..."
                        value={currentTodo?.description}
                        onChange={(value) =>
                          setCurrentTodo({
                            ...currentTodo,
                            description: value.target.value,
                          })
                        }
                      />
                    </div>
                    <div className="col d-flex align-items-center justify-content-end">
                      <h5 className="m-0 p-0 px-2">
                        <button
                          type="button"
                          className="btn btn-danger"
                          onClick={closePopup}
                        >
                          Close
                        </button>
                      </h5>
                      <h5 className="m-0 p-0 px-2">
                        <button
                          type="button"
                          className="btn btn-primary"
                          onClick={updateDescription}
                        >
                          Save
                        </button>
                      </h5>
                    </div>
                  </div>
                </>
              }
            />
          )}
        </div>

        {/* DELETE DONE AND DELETE ALL BUTTONS */}
        <div className="mt-4 mb-4 col d-flex align-items-center justify-content-center">
          <h5 className="m-0 p-0 px-2">
            <button
              type="button"
              className="btn btn-danger p-2"
              onClick={deleteCompletedTasks}
            >
              Delete Done Tasks
            </button>
          </h5>
          <h5 className="m-0 p-0 px-2">
            <button
              type="button"
              className="btn btn-danger p-2"
              onClick={deleteAllTasks}
            >
              Delete All Tasks
            </button>
          </h5>
        </div>
      </div>
    </>
  );
}

export default HomePage;
