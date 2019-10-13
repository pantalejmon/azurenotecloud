import React, {Component} from 'react';
const ReactDOM = require('react-dom');
import axios from 'axios'
const when = require('when');
const client = require('../client');
const root = '../api';

export class  Home extends Component {
    constructor(props) {
        super(props);
        this.state = {user: []};

    }

    componentDidMount() {
        axios.get('/user/current')
            .then(response => {
                this.setState({user: response.data});

            })
            .catch(error => {
                console.log(error);
            });

    }
    render() {
        return(
            <div>
                Hello {this.state.user.name + " " + this.state.user.lastName}
            </div>
        );
    }

}
