import type React from 'react';
import { useEffect, useState } from 'react';

import ReactDOM from 'react-dom';

const Portal = ({
  children,
  selector,
}: {
  children: React.ReactNode;
  selector: string;
}) => {
  const [mounted, setMounted] = useState(false);
  useEffect(() => {
    setMounted(true);
  }, []);

  const element =
    typeof window !== 'undefined' && document.querySelector(selector);

  return mounted && element && children
    ? ReactDOM.createPortal(children, element)
    : // eslint-disable-next-line no-null/no-null
      null;
};

export default Portal;
