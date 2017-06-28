/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
    config.toolbarGroups = [
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'forms', groups: [ 'forms' ] },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'links', groups: [ 'links' ] },
        { name: 'insert', groups: [ 'insert' ] },
        { name: 'pbckcode', groups: [ 'pbckcode' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'tools', groups: [ 'tools' ] },
        '/',
        { name: 'styles', groups: [ 'styles' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];

    config.removeButtons = 'Source,Form,Checkbox,Radio,TextField,Textarea,Select,Button,ImageButton,HiddenField,CreateDiv,Flash,Iframe,Anchor,About';

    // CKEDITOR PLUGINS LOADING
    config.extraPlugins = 'pbckcode'; // add other plugins here (comma separated)

    // ADVANCED CONTENT FILTER (ACF)
    // ACF protects your CKEditor instance of adding unofficial tags
    // however it strips out the pre tag of pbckcode plugin
    // add this rule to enable it, useful when you want to re edit a post
    // only needed on v1.1.x
    //config.allowedContent = 'pre[*]{*}(*)'; // add other rules here

    // PBCKCODE CUSTOMIZATION
    config.pbckcode = {
        // An optional class to your pre tag.
        cls: '',

        // The syntax highlighter you will use in the output view
        highlighter: 'PRETTIFY',

        // An array of the available modes for you plugin.
        // The key corresponds to the string shown in the select tag.
        // The value correspond to the loaded file for ACE Editor.
        modes: [['HTML', 'html'], ['CSS', 'css'], ['PHP', 'php'], ['JS', 'javascript']
                ,['C/C++', 'c_cpp'], ['Java', 'java'], ['Python', 'python'], ['SQL', 'sql']],


        // The theme of the ACE Editor of the plugin.
        theme: 'tomorrow_night_eighties',

        // Tab indentation (in spaces)
        tab_size: '4'
    };
};
