/*    -*- Java -*-
 *
 *  Copyright 2012 Tail-F Systems AB. All rights reserved.
 *
 *  This software is the confidential and proprietary
 *  information of Tail-F Systems AB.
 *
 *  $Id$
 *
 */

package com.tailf.confm.yang;

import com.tailf.confm.ConfMException;

/**
 * Implements the built-in YANG data type "identityref".
 * 
 * @author emil@tail-f.com
 */
public class Identityref extends Type<Statement> {

    /**
     * Generated serial version UID, to be changed if this class is modified in
     * a way which affects serialization. Please see:
     * http://docs.oracle.com/javase/6/docs/platform/serialization/spec/version.html#6678
     */
    private static final long serialVersionUID = -482307288484824804L;
    
    /**
     * Creates a YangType object from a String, formatted as described in
     * {@link Identityref#fromString(String)}.
     * 
     * @param s The string.
     * @throws ConfMException If s is improperly formatted.
     */
    public Identityref(String s) throws ConfMException {
        super(s);
    }
    
    /**
     * Creates a YangType object from a Statement.
     * 
     * @param value The initial value of the new YangType object.
     * @throws ConfMException If an invariant was broken during initialization.
     */
    public Identityref(Statement identity) throws ConfMException {
        super(identity);
    }
    
    /**
     * Creates a YangType object from three strings: identity
     * argument/identifier and the identity module namespace and prefix.
     *
     * @param id identity argument/identifier
     * @param ns identity module namespace
     * @param prefix identity module prefix
     * @throws ConfMException If an invariant was broken during initialization.
     */
    public Identityref(String id, String ns, String prefix)
            throws ConfMException {
        super(id + " " + ns + " " + prefix);
    }

    /**
     * Returns a Statement from a String.
     * <p>
     * The string should contain space separated tokens, ordered as follows:
     * identity argument/identifier, identity namespace uri, prefix
     * 
     * @param s A string representation of a value of type T.
     * @return A T value parsed from s.
     * @throws ConfMException If s does not contain a parsable T.
     */
    @Override
    protected Statement fromString(String s) throws ConfMException {
        String[] ss = s.split(" ");
        if (ss.length == 3) {
            Statement module = new Statement("module", "<unknown>");
            module.addChild(new Statement("namespace", ss[1]));
            module.addChild(new Statement("prefix", ss[2]));
            return new Statement("Identity", ss[0], module, module, null);
        } else {
            throw new ConfMException(ConfMException.BAD_VALUE, s);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.tailf.confm.yang.Type#canEqual(java.lang.Object)
     */
    @Override
    public boolean canEqual(Object obj) {
        return obj instanceof Identityref || obj instanceof Statement;
    }

}
