function HomePage() {
    
    return (
        <div className="container m-5 p-2 rounded mx-auto bg-light shadow">
            <div className="row m-1 p-4">
                <div className="col">
                    <div className="p-1 h1 text-center mx-auto display-inline-block">
                        <h1>TodoInput</h1>
                    </div>
                </div>
            </div>
            <div className="row m-1 p-3">
                <div className="col col-11 mx-auto">
                    <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                        <div className="col">
                            <input 
                                type="text" 
                                className="form-control form-control-lg border-0 add-todo-input bg-transparent rounded" 
                                placeholder="New Todo"/>
                        </div>
                        <div className="col-auto px-0 mx-0 mr-2">
                            <button type="button" className="btn btn-primary btn-lg btn-block">Add New Task</button>
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
            
            <div className="row mx-1 px-5 pb-3 w-80">
                <div className="col mx-auto">
                    <div className="row px-3 align-items-center todo-item rounded border">
                        <div className="col-auto m-1 p-0 d-flex align-items-center">
                            <h2 className="m-0 p-0">
                                <i className="fa fa-square-o text-primary btn m-0 p-0 d-none" data-toogle="tooltip" data-placement="bottom" title="Mark as Complete"></i>
                                <input type="checkbox" className="form-control-lg" />
                            </h2>
                        </div>
                        <div className="col px-1 m-1 d-flex align-items-center">
                            <input type="text" className="form-control border-0 edit-todo-input bg-transparent rounded px-3 text-x1" title="Todo Item #1" readOnly disabled/>
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