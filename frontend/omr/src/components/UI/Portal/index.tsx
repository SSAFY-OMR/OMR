import type React from 'react';

import ReactDOM from 'react-dom';

const Portal = ({
  children,
  selector,
}: {
  children: React.ReactNode;
  selector: string;
}) => {
  const element =
    typeof window !== 'undefined' && document.querySelector(selector);
  // eslint-disable-next-line no-null/no-null
  return element && children ? ReactDOM.createPortal(children, element) : null;
};

export default Portal;
