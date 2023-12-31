import { useEffect, useState } from "react";
// Popup Modal
import Popup from "../component/Popup";
// Notyf
import {Notyf} from 'notyf';
import 'notyf/notyf.min.css';
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

  // Notyf 
  const notyf = new Notyf({
    position: {
      x:'right',
      y:"top"
    },
    duration:1000
  });

  // Toast for Add New Task
  const addNewTaskToast = () => {
    notyf.success("New task is added.");
  }
  // Toast for Delete Task
  const deleteTaskToast = () => {
    notyf.error("Task is deleted.");
  }
  // Toast for Update Task
  const updateTaskToast = () => {
    notyf.success("Task is updated.");
  }
  // Toast for Delete All Tasks
  const deleteAllTasksToast = () => {
    let notyf = new Notyf({
      position:{
        x:"right",
        y:"bottom"
      },
      duration:1000,
      types:[
        {
          type:'custom',
          background:'black'
        }
      ]
    });
    notyf.open({
      type:'custom',
      message:'<b>All Tasks</b> are deleted !!!'
    });
  }
  // Toast for Delete Done Tasks
  const deleteDoneTasksToast = () => {
    let notyf = new Notyf({
      position:{
        x:"right",
        y:"bottom"
      },
      duration:1000
    });
    notyf.error("Done Tasks are deleted.");
  }

  // GET TODO LIST
  // React her render (yenileme) sonrası useEffect fonksiyonu çalıştıtır. Her render sonrası liste yenilenir. 
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
        addNewTaskToast();
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
        deleteTaskToast();
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
        updateTaskToast();
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
        deleteAllTasksToast();
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
        deleteDoneTasksToast();
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
              <div className="col col-auto bg-info text-white p-2 m-0">
                <i className="fa-solid fa-list-check fa-xl"></i>
              </div>
              <div className="col-11">
                <input
                  type="text"
                  className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                  placeholder="New Todo"
                  onChange={(value) => setNewTaskInput(value.target.value)}
                />
              </div>
            </div>
            <div className="row px-0 mx-0 mt-4">
              <button
                type="button"
                className="btn btn-info btn-lg btn-block"
                onClick={addNewTask}
              >
                Add New Task
              </button>
            </div>
          </div>
        </div>
      </div>

      <div className="container m-4 p-2 rounded mx-auto bg-light shadow border">
        {/* FILTERING OF LIST OPTIONS */}
        <div className="row mt-2 p-3 px-5 justify-content-end">
          <div className="d-flex justify-content-center">
            <h2>TodoList</h2>
          </div>
          <div className="row">
            <button
              className="btn btn-info btn-md col m-3"
              onClick={getAllTasks}
            >
              All
            </button>
            <button
              className="btn btn-info btn-md col m-3"
              onClick={getCompletedTasks}
            >
              Completed
            </button>
            <button
              className="btn btn-info btn-md col m-3"
              onClick={getUncompletedTasks}
            >
              Uncompleted
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
                        className="fa fa-square-o text-info btn mb-0 p-0 d-none"
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
                    <b className="text-info">UPDATE YOUR TODO</b>
                    <div className="row px-3 align-items-center todo-item rounded border mt-4 mb-4">
                      <textarea
                        type="input"
                        rows="1"
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
                          className="btn btn-success"
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
        <div className="row container mt-4 mb-4 align-items-center justify-content-center">
          <button
            type="button"
            className="btn btn-danger col-5 p-2 m-1"
            onClick={deleteCompletedTasks}
          >
            Delete Done Tasks
          </button>

          <button
            type="button"
            className="btn btn-danger col-5 p-2 m-1"
            onClick={deleteAllTasks}
          >
            Delete All Tasks
          </button>
        </div>
      </div>
    </>
  );
}

export default HomePage;
