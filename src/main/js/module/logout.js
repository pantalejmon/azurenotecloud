import React, { Component } from "react";
import PropTypes from "prop-types";
import { Redirect } from "react-router-dom";
const ReactDOM = require('react-dom');

const when = require('when');
const client = require('../client');
const root = '../api';

export class Logout extends Component {

    constructor(props) {
        super(props);
        this.state = {};
    }
    componentDidMount() {
        client({method: 'GET', path: '/logout'}).done(response => {
            window.location.reload();

        });
    }
    render() {
        return (<Redirect to="/index" />);
    }

}
