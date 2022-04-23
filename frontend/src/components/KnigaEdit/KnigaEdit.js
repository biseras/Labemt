import React from 'react';
import {useNavigate} from 'react-router-dom';

const KnigaEdit = (props) => {

    const history = useNavigate();

    const [formData, updateFormData] = React.useState({
        name: "",
        category: 1,
        avtor: 1,
        availablecopies: 2
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.kniga.name;
        const avtor = formData.avtor !== 0 ? formData.avtor : props.kniga.avtor.id;
        const category = formData.category !== 0 ? formData.category : props.kniga.category;
        const availablecopies = formData.availablecopies !== 0 ? formData.availablecopies : props.kniga.availablecopies;
        props.onEditKniga(props.kniga.id, name, category, avtor, availablecopies);
        history("/kniga");
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.kniga.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.category.map((term) => {
                                if(props.kniga.category !== undefined &&
                                    props.kniga.category === term.category)
                                    return <option selected={props.kniga.category} value={term.id}>{term}</option>
                                else return <option value={term}>{term}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="avtor" className="form-control" onChange={handleChange}>
                            {props.avtor.map((term) => {
                                if(props.kniga.avtor !== undefined &&
                                    props.kniga.avtor.id === term.id)
                                    return <option selected={props.kniga.avtor.id} value={term.id}>{term.name + " " + term.surname}</option>
                                else return <option value={term.id}>{term.name + " " + term.surname}</option>
                            })}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availablecopies">Available Copies</label>
                        <input type="number"
                               className="form-control"
                               id="availablecopies"
                               name="availablecopies"
                               placeholder={props.kniga.availablecopies}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}
export default KnigaEdit;
