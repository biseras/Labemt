import React, {Component} from "react";
import ReactPaginate from "react-paginate";
import {Link} from "react-router-dom";

class Kniga extends Component{
    constructor(props) {
        super(props);
        this.state={
            page:0,
            size:2
        }
    }
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const bookPageCount = Math.ceil(this.props.kniga.length / this.state.size);
        const kniga=this.getKnigaPage(offset, nextPageOffset);
        return(
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Country</th>
                                <th scope={"col"}>Avtor</th>
                                <th scope={"col"}>Availablecopies</th>
                                <th scope={"col"}>buttons</th>
                            </tr>
                            </thead>
                            <tbody>
                            {kniga}
                            </tbody>
                        </table>
                    </div>
                    <ReactPaginate
                        previousLabel={"Back"}
                        style={{ textDecoration: 'none' }}
                        previousClassName={"page-link"}
                        nextLabel={"Next"}
                        nextClassName={"page-link"}
                        breakLabel={<a href="/#">...</a>}
                        breakClassName={"break-me"}
                        pageClassName={"ml-1 page-link"}
                        pageCount={bookPageCount}
                        marginPagesDisplayed={2}
                        pageRangeDisplayed={5}
                        onPageChange={this.handlePageClick}
                        containerClassName={"pagination m-4 justify-content-center"}
                        activeClassName={"active"}
                    />                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/kniga/add"}>Add new product</Link>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }
    getKnigaPage = (offset, nextPageOffset) => {
        return this.props.kniga.map((term) => {
            return (
                            <tr key={term.id}>
                                <td>{term.name}</td>
                                <td>{term.category}</td>
                                <td>{term.avtor.name + " " + term.avtor.surname}</td>
                                <td>{term.availablecopies}</td>
                                <td>
                                    <a title={"Delete"} className={"btn btn-danger"} onClick={() => this.props.onDelete(term.id)}>
                                        Delete
                                    </a>
                                    <a title={"Knigataezemena"} className={"btn btn-primary"}
                                       onClick={() => this.props.knigataezemena(term.id)}>Zemi ja knigata</a>
                                    <Link className={"btn btn-info"}
                                          onClick={() => this.props.onEdit(term.id)}
                                          to={`/kniga/edit/${term.id}`}>
                                        Edit
                                    </Link>
                                </td>
                            </tr>
            );
        }).filter((kniga, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Kniga;