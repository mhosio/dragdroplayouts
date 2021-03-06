/*
 * Copyright 2014 John Ahlroos
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package fi.jasoft.dragdroplayouts.client.ui.accordion;

import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ui.dd.VAcceptCallback;
import com.vaadin.client.ui.dd.VDragEvent;

import fi.jasoft.dragdroplayouts.client.ui.VDDAbstractDropHandler;

public class VDDAccordionDropHandler extends VDDAbstractDropHandler<VDDAccordion> {

  public VDDAccordionDropHandler(ComponentConnector connector) {
    super(connector);
  }

  @Override
  protected void dragAccepted(VDragEvent drag) {
    dragOver(drag);
  }

  @Override
  public boolean drop(VDragEvent drag) {
    getLayout().deEmphasis();
    getLayout().updateDropDetails(drag);
    return getLayout().postDropHook(drag) && super.drop(drag);
  };

  @Override
  public void dragOver(VDragEvent drag) {

    getLayout().deEmphasis();

    getLayout().updateDropDetails(drag);

    getLayout().postOverHook(drag);

    // Validate the drop
    validate(new VAcceptCallback() {
      public void accepted(VDragEvent event) {
        getLayout().emphasis(event.getElementOver(), event);
      }
    }, drag);
  };

  @Override
  public void dragLeave(VDragEvent drag) {
    getLayout().deEmphasis();
    getLayout().updateDropDetails(drag);
    getLayout().postLeaveHook(drag);
  }
}
