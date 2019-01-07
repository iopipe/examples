
interface IOPipeConfig {
  debug?: boolean;
  token?: string;
}

declare module "@iopipe/iopipe" {
    export function label(label: string): void;

    export default function iopipe(config?: IOPipeConfig): Function;
}