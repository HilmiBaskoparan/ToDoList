import { useEffect, useState } from "react";

import { 
    getAllTasksService, 
    addTaskService, 
    updateTaskService } from "../service/services";

function HomePage() {

    const [allItems, setAllItems] = useState([]);
    const [newTaskInput, setNewTaskInput] = useState("");
    
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
          });
      };

      // <Update Todo Task When Checked>
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
          .catch((err) => {
            console.log(err.response.data.message);
          });
      };
    
    return (
        <div className="container m-5 p-2 rounded mx-auto bg-light shadow">

            {/* TITLE */}
            <div className="row m-1 p-4">
                <div className="col">
                    <div className="p-1 h1 text-center mx-auto display-inline-block">
                        <h1>TodoInput</h1>
                    </div>
                </div>
            </div>

            {/* ADD NEW TASK */}
            <div className="row m-1 p-3">
                <div className="col col-11 mx-auto">
                    <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                        <div className="col">
                            <input 
                                type="text" 
                                className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" 
                                placeholder="New Todo"
                                onChange={(value) => setNewTaskInput(value.target.value)}/>
                        </div>
                        <div className="col-auto px-0 mx-0 mr-2">
                            <button 
                                type="button" 
                                className="btn btn-primary btn-lg btn-block"
                                onClick={addNewTask}>Add New Task</button>
                        </div>
                    </div>
                </div>
            </div>

            <div className="p-2 mx-4 border-bottom"></div>

            <div className="row m-1 p-3 px-5 justify-content-end">
                <div class="d-flex justify-content-center">
                    <h2>TodoList</h2>
                </div>
                <div className="row">
                    <button className="btn btn-primary btn-md col m-3">All</button>
                    <button className="btn btn-primary btn-md col m-3">Completed</button>
                    <button className="btn btn-primary btn-md col m-3">InCompleted</button>
                </div>
            </div>
            
            {/* SHOW DATA LIST */}
            {allItems?.map((task) => (
                <div className="row mx-1 px-5 pb-3 w-80">
                    <div className="col mx-auto">
                        <div className="row px-3 align-items-center todo-item rounded border">
                            <div className="col-auto m-1 p-0 d-flex align-items-center">
                                <h2 className="m-0 p-0">
                                    <i className="fa fa-square-o text-primary btn m-0 p-0 d-none" data-toogle="tooltip" data-placement="bottom" title="Mark as Complete"></i>
                                    <input 
                                        type="checkbox" 
                                        className="form-control-lg"
                                        checked={task.isDone}
                                        onChange={() => updateIsDone(task)}></input>
                                </h2>
                            </div>
                            <div className="col px-1 m-1 d-flex align-items-center">
                                <input 
                                    style={
                                        task?.isDone ? {textDecoration: "line-through", color: "red"} : {textDecoration: "none"}
                                    }
                                    type="text" 
                                    className="form-control border-0 edit-todo-input bg-transparent rounded px-3 text-x1" 
                                    title="Todo Item #1" 
                                    readOnly 
                                    disabled
                                    value={task?.description}/>
                            </div>

                            <div className="col-auto m-1 p-0 px-3 d-none"></div>

                            <div className="col-auto m-1 p-0 todo-actions">
                                <div className="col d-flex align-items-center justify-content-end">
                                    <h5 className="m-0 p-0 px-2">
                                        <button type="button" className="btn btn-primary">Edit</button>
                                    </h5>
                                    <h5 className="m-0 p-0 px-2">
                                        <button type="button" className="btn btn-danger">Delete</button>
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            ))};
            
            <div className="mt-4 mb-4 col d-flex align-items-center justify-content-center">
                <h5 className="m-0 p-0 px-2">
                    <button type="button" className="btn btn-danger">Delete Done Tasks</button>
                </h5>
                <h5 className="m-0 p-0 px-2">
                    <button type="button" className="btn btn-danger">Delete All Tasks</button>
                </h5>
            </div>
        </div>
    );

}

export default HomePage;