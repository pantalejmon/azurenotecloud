import React, {Component} from 'react';

import RichTextEditor from 'react-rte';
import PropTypes from "prop-types";
import axios from 'axios'
import { Redirect, Link,  } from "react-router-dom";
import {Button,Icon, Progress} from "semantic-ui-react";

const ReactDOM = require('react-dom');

var globs = require('../global_vars');

class AddNotes extends Component
{
    constructor(props){
        super(props);
        this.state = {
            value: RichTextEditor.createEmptyValue(),
            percent:0

        }
        this.id = globs.idToChange;
        this.dt = this.dt = {id: [], Note: [], users: [], shareuser: []};
        this.handleClickSave = this.handleClickSave.bind(this);
        if (this.id !== -1){
            const url = '/user/notes/'+ this.id.toString();
            console.log("url ",url);
            axios.get(url)
                .then(response => {
                    console.log(response.data.content);
                    this.setState({
                        value: RichTextEditor.createValueFromString(response.data.content, 'html')
                    })
                })
                .catch(error => {
                    console.log(error);
                })
        }

    }

    increment = () =>
        this.setState({
            percent: this.state.percent >= 100 ? 0 : this.state.percent + 50,
        })

    static propTypes = {
        onChange: PropTypes.func
    };

    handleClickSave(e) {
        this.increment(this);
        e.preventDefault();
        console.log(this.state.value.toString('html'));
        this.dt = {content: this.state.value.toString('html')};
        var url = '/user/notes/';

        if (this.id !== -1){
            url = '/user/notes/'+ this.id.toString();
            this.increment(this);
            console.log("url ",url);
            axios({
                    method: 'put',
                    url: url,
                    data:JSON.stringify(this.dt),
                    headers: {'Content-Type': 'application/json'}}
                )
                .then(function (response) {
                    // console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        else{
            this.increment(this);
            axios.post(
                url, JSON.stringify(this.dt),
                {headers: {'Content-Type': 'application/json'}}
            )
                .then(function (response) {
                    // console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        this.increment(this);

    }



    onChange = (value) => {
        this.setState({value});
        if (this.props.onChange) {
            // Send the changes up to the parent component as an HTML string.
            // This is here to demonstrate using `.toString()` but in a real app it
            // would be better to avoid generating a string on each change
            this.props.onChange(
                value.toString('html')

            );
        }
    };

    render(){
        return(
            <div>

                <h3>
                    Notepad
                </h3>

                <div>
                    <RichTextEditor
                        value={this.state.value}
                        onChange={this.onChange}
                    />
                </div>

                    <Button
                        icon='save'
                        onClick={this.handleClickSave}
                        primary
                    />

            </div>
        );

    }
}

export {AddNotes};