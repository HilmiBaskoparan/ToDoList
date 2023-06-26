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
        </div>
    );

}

export default HomePage;