import axios from "../costum-axios/axios";
const LabService={
    fetchCountry:()=>{
        return axios.get("/country");
    },
    fetchAvtor:()=>{
        return axios.get("/avtor");
    },
    fetchKniga:()=>{
        return axios.get("/kniga");
    },

    fetchCategory: () => {
        return axios.get("/categories")
    },

    deleteKniga: (id) => {
        return axios.delete(`/kniga/delete/${id}`);
    },
    addKniga: (name, category, avtor, availablecopies) => {
        return axios.post("/kniga/add", {
            "name" : name,
            "category" : category,
            "avtor" : avtor,
            "availablecopies" : availablecopies
        });
    },
    editKniga: (id, name, category, avtor, availablecopies) => {
        return axios.put(`/kniga/edit/${id}`, {
            "name" : name,
            "category" : category,
            "avtor" : avtor,
            "availablecopies" : availablecopies
        });
    },
    knigataezemena: (id) => {
        return axios.put(`/kniga/knigataezemena/${id}`)
    },
    getKniga: (id) => {
        return axios.get(`/kniga/${id}`)
    }


}
export default LabService;