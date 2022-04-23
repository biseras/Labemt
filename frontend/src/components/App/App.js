import './App.css';
import React from "react";
import {Component} from "react";
import Country from '../Country/country';
import Kniga from '../Kniga/kniga'
import Avtor from '../Avtor/avtor'
import Header from '../Header/header'
import KnigaAdd from '../Kniga/KnigaAdd/knigaadd'
import KnigaEdit from "../KnigaEdit/KnigaEdit";
import Category from '../Kategorija/kategorija'
import {
    Routes,
    Navigate,
    Route
} from 'react-router-dom';
import LabService from "../../repository/LabRepository";

class App extends Component{
  constructor(props) {
    super(props);
    this.state={
      country:[],
      avtor:[],
      kniga:[],
        category:[],
        selectedKniga: {}
    }
  }
  render() {
    return (

            <main>
            <Header/>
                <div className="container">
                    <Routes>
                        <Route path="/country" exact element={<Country country={this.state.country}/>}/>
                        <Route path="/avtor" exact element={<Avtor avtor={this.state.avtor}/>}/>
                        <Route path="/category" exact element={<Category category={this.state.category}/>}/>
                        <Route path="/kniga/add" exact element={<KnigaAdd category={this.state.category}
                                                                         avtor={this.state.avtor}
                                                                         onAddKniga={this.addKniga}/>}/>
                        <Route path="/kniga/edit/:id" exact element={<KnigaEdit category={this.state.category}
                                                                               avtor={this.state.avtor}
                                                                               onEditKniga={this.editKniga}
                                                                               kniga={this.state.selectedKniga}/>}/>
                        <Route path={"/kniga"} exact element={<Kniga kniga={this.state.kniga} onDelete={this.deleteKniga}                                                                     markAsTaken={this.markAsTaken}
                                                                     knigataezemena={this.knigataezemena}
                                                                     onEdit={this.getKniga}/>}/>
                        <Route path="/" element={<Navigate to={"/kniga"}/>}/>
                    </Routes>
                </div>
            </main>

    );
  }
  loadCountry = () => {
    LabService.fetchCountry()
        .then((data)=> {
          this.setState({
            country: data.data
          })
        });
  }
  loadAvtor = () => {
    LabService.fetchAvtor()
        .then((data)=> {
          this.setState({
            avtor: data.data
          })
        });
  }
  loadCategory = () => {
        LabService.fetchCategory()
            .then((data)=>{
                this.setState({
                    category: data.data
                })
            })
    }
  loadKniga = () => {
    LabService.fetchKniga()
        .then((data)=> {
          this.setState({
            kniga: data.data
          })
        });
  }
    deleteKniga = (id) => {
        LabService.deleteKniga(id)
            .then(() => {
                this.loadKniga();
            });
    }

    addKniga = (name, category, avtor, availablecopies) => {
        LabService.addKniga(name, category, avtor, availablecopies)
            .then(() => {
                this.loadKniga();
            })
    }

    getKniga = (id) => {
        LabService.getKniga(id)
            .then((data) => {
                this.setState({
                    selectedKniga: data.data
                })
            })
    }
    knigataezemena = (id) => {
        LabService.knigataezemena(id)
            .then(() => {
                this.loadKniga();
            });
    }

    editKniga = (id, name, category, avtor, availablecopies) => {
        LabService.editKniga(id, name, category, avtor, availablecopies)
            .then(() => {
                this.loadKniga();
            });
    }



    componentDidMount() {
    this.loadCountry()
    this.loadAvtor()
    this.loadKniga()
        this.loadCategory()
  }


}

export default App;